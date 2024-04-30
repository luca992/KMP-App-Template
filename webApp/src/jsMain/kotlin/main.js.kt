import androidx.compose.runtime.LaunchedEffect
import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.MR
import com.jetbrains.kmpapp.di.initKoin
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.provider.JsStringProvider
import kotlinx.js.JsPlainObject
import org.jetbrains.compose.web.renderComposable


@JsModule("./globals.css")
external val cssFile: dynamic

var stringsLoader: JsStringProvider? = null

fun stringResource(resource: StringResource): String {
    return stringsLoader?.let { loader ->
        resource.localized(loader, null)
    } ?: "stringsLoader is not initialized"
}

@JsPlainObject
external interface Foo {
    val bar: Long
}

fun main() {
    cssFile
    initKoin()
    console.log(JSON.stringify(Foo(bar = 42)))
    renderComposable(rootElementId = "root") {
        LaunchedEffect(Unit) {
            stringsLoader = MR.strings.stringsLoader.getOrLoad()
        }
        App()
    }
}

