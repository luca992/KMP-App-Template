import androidx.compose.runtime.Composable
import com.jetbrains.kmpapp.data.MuseumObject
import dev.kilua.Application
import dev.kilua.compose.root
import dev.kilua.core.IComponent
import dev.kilua.html.div
import dev.kilua.html.h3
import dev.kilua.html.img
import dev.kilua.utils.JsModule
import dev.kilua.utils.JsNonModule
import dev.kilua.utils.useModule

@JsModule("./css/style.css")
@JsNonModule
external object css

class App : Application() {

    init {
        useModule(css)
    }

    override fun start() {
        root("root") {
            div(
                className = "text-center text-4xl font-bold p-10 bg-red-100 dark:bg-red-800 dark:text-red-200"
            ) {
                +"Hello World!"
            }
            ObjectFrame(
                obj = MuseumObject(
                    objectID = 1,
                    title = "Title",
                    primaryImageUrl = "https://via.placeholder.com/150",
                    artistDisplayName = "Artist",
                    medium = "phasellus",
                    dimensions = "saperet",
                    objectURL = "http://www.bing.com/search?q=scripta",
                    objectDate = "tale",
                    primaryImageSmallUrl = "https://duckduckgo.com/?q=quam",
                    repository = "volumus",
                    department = "verear",
                    creditLine = "adolescens",

                    ),
                onClick = {}
            )
        }
    }
}


@Composable
private fun IComponent.ObjectFrame(
    obj: MuseumObject,
    onClick: () -> Unit,
) {
    div {
        onClick { onClick() }
        className(
            listOf(
                "bg-white",
                "hover:bg-blue-200",
                "dark:bg-slate-800",
                "rounded-lg",
                "px-6",
                "py-8",
                "ring-1",
                "ring-slate-900/5",
                "shadow-xl"
            ).joinToString(" ")
        )
        div {
            className(
                listOf("flex", "justify-center", "w-full").joinToString(" ")
            )

            img(
                src = obj.primaryImageSmall, alt = obj.title
            ) {
                className("h-32 w-32 object-scale-down")
            }
        }

        h3 {
            className(
                "text-slate-900 dark:text-white mt-5 text-base font-medium tracking-tight"
            )
            +obj.title
        }
//        P({ classes("text-slate-500", "dark:text-slate-400", "mt-2", "text-sm") }) {
//            Text(obj.artistDisplayName)
//        }
//        P({ classes("text-slate-500", "dark:text-slate-400", "mt-2", "text-sm") }) {
//            Text(obj.objectDate)
//        }
    }
}

