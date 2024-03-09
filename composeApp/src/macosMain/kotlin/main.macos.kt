import androidx.compose.ui.window.Window
import com.jetbrains.kmpapp.App
import com.jetbrains.kmpapp.di.initKoin
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

public fun main() {
    initKoin()
    NSApplication.sharedApplication()
    Window("KMP App") {
        App()
    }
    NSApp?.run()
}