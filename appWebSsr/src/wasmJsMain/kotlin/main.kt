import com.jetbrains.kmpapp.App
import dev.kilua.TailwindcssModule
import dev.kilua.startApplication

fun main() {
    startApplication(
        ::App,
        null,
        TailwindcssModule,
    )
}