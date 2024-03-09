import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
public fun main() {
    initKoin()
    CanvasBasedWindow("KMP App") {
        App()
    }
}