package com.jetbrains.kmpapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.list.ListScreen
import dev.kilua.Application
import dev.kilua.compose.root
import dev.kilua.html.div
import dev.kilua.ssr.SsrRouter


//@JsModule("./css/style.css")
//@JsNonModule
//external object css


class App : Application() {

    init {
//  custom css disabled because of Kilua plugin bug
//        useModule(css)
//        CssRegister.register("css/style.css")
        initKoin()
    }

    override fun start() {
        root("root") {
            withViewModelStoreOwner {
                SsrRouter(
                    initPath = "/"
                ) { ->
                    route("/") {
                        ListScreen()
                    }
                    route("detail") { ->
                        int { objectId ->
                            // DetailScreen(objectId!!)
                            div { // TODO: replace with DetailScreen
                                +"Detail Screen for $objectId"
                            }
                        }
//                        noMatch {
                        //                      }
                    }
                    //                noMatch {
                    //              }
                }
            }
        }
    }
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
        // Normal case: use system-provided owner
        content()
    } else {
        // Fallback case: use ViewModelStoreOwner with scope of this composable.
        // It's required for Compose Multiplatform for now because it's not providing default value yet.
        // Expected to be fixed in Compose Multiplatform 1.7.0
        CompositionLocalProvider(
            LocalViewModelStoreOwner provides rememberComposeViewModelStoreOwner(), content = content
        )
    }
}