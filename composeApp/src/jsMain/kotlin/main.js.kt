import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.di.initKoin
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
public fun main() {
    initKoin()
    onWasmReady {
        CanvasBasedWindow("KMP App") {
            App()
        }
    }
}