import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP App",
        state = rememberWindowState(width = 1050.dp, height = 700.dp),
    ) {
        App()
    }
}
