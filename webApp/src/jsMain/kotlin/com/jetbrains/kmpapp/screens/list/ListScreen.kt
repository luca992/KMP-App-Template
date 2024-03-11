package com.jetbrains.kmpapp.screens.list


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import org.jetbrains.compose.web.dom.*

data object ListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: ListScreenModel = getScreenModel()

        val objects by screenModel.objects.collectAsState()

        if (objects.isNotEmpty()) {
            ObjectGrid(objects = objects, onObjectClick = { objectId ->
                    navigator.push(DetailScreen(objectId))
            })
        } else {
            EmptyScreenContent()
        }
    }

}

@Composable
private fun ObjectGrid(
    objects: List<MuseumObject>,
    onObjectClick: (Int) -> Unit,
) {
    Div({ classes("p-5","grid", "grid-cols-2", "sm:grid-cols-3", "md:grid-cols-3", "lg:grid-cols-5", "gap-4") }) {
        objects.forEach { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.objectID) },
            )
        }
    }
}

@Composable
private fun ObjectFrame(
    obj: MuseumObject,
    onClick: () -> Unit,
) {
    Div({
        onClick { onClick() }
        classes(
            "bg-white",
            "hover:bg-blue-200",
            "dark:bg-slate-800",
            "rounded-lg",
            "px-6",
            "py-8",
            "ring-1",
            "ring-slate-900/5",
            "shadow-xl"
        )
    }) {
        Div({ classes("flex", "justify-center", "w-full") }) {
            Img(
                src = obj.primaryImageSmall, alt = obj.title
            ) {
                classes("h-32", "w-32", "object-scale-down")
            }
        }
        H3({
            classes(
                "text-slate-900", "dark:text-white", "mt-5", "text-base", "font-medium", "tracking-tight"
            )
        }) {
            Text(obj.title)
        }
        P({ classes("text-slate-500", "dark:text-slate-400", "mt-2", "text-sm") }) {
            Text(obj.artistDisplayName)
        }
        P({ classes("text-slate-500", "dark:text-slate-400", "mt-2", "text-sm") }) {
            Text(obj.objectDate)
        }
    }
}
