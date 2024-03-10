import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.di.initKoin
import org.jetbrains.compose.web.renderComposable


@JsModule("./globals.css")
external val cssFile: dynamic

fun main() {
    cssFile
    initKoin()
    renderComposable(rootElementId = "root") {
        App()
    }
}

