import com.jetbrains.kmpapp.App
import dev.kilua.Hot
import dev.kilua.TailwindcssModule
import dev.kilua.startApplication

fun main() {
    startApplication(
        ::App,
        js("import.meta.webpackHot").unsafeCast<Hot?>(),
        TailwindcssModule,
    )
}
