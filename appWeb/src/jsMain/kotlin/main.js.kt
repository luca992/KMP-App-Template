import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.MR
import com.jetbrains.kmpapp.di.initKoin
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.provider.JsStringProvider
import org.jetbrains.compose.web.renderComposable


@JsModule("./globals.css")
external val cssFile: dynamic

var stringsLoader: JsStringProvider? = null

fun stringResource(resource: StringResource): String {
    return stringsLoader?.let { loader ->
        resource.localized(loader, null)
    } ?: "stringsLoader is not initialized"
}

private class ComposeViewModelStoreOwner : ViewModelStoreOwner {
    override val viewModelStore = ViewModelStore()
    fun dispose() {
        viewModelStore.clear()
    }
}

@Composable
private fun rememberComposeViewModelStoreOwner(): ViewModelStoreOwner {
    val viewModelStoreOwner = remember { ComposeViewModelStoreOwner() }
    DisposableEffect(viewModelStoreOwner) {
        onDispose { viewModelStoreOwner.dispose() }
    }
    return viewModelStoreOwner
}

@Composable
internal fun withViewModelStoreOwner(content: @Composable () -> Unit) {
    if (LocalViewModelStoreOwner.current != null) {
        console.log("LocalViewModelStoreOwner is not null")
        // Normal case: use system-provided owner
        content()
    } else {
        console.log("LocalViewModelStoreOwner is null")
        // Fallback case: use ViewModelStoreOwner with scope of this composable.
        // It's required for Compose Multiplatform for now because it's not providing default value yet.
        // Expected to be fixed in Compose Multiplatform 1.7.0
        CompositionLocalProvider(
            LocalViewModelStoreOwner provides rememberComposeViewModelStoreOwner(), content = content
        )
    }
}

fun main() {
    cssFile
    initKoin()
    renderComposable(rootElementId = "root") {
        withViewModelStoreOwner {
            var stringsLoaderReady by remember { mutableStateOf(true) }
            LaunchedEffect(Unit) {
                stringsLoader = MR.strings.stringsLoader.getOrLoad()
                stringsLoaderReady = true
            }
            if (stringsLoaderReady) {
                App()
            }
        }
    }
}

