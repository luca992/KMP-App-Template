package com.jetbrains.kmpapp.screens.list


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.koin.compose.koinInject

@Composable
fun ListScreen(
    navController: NavController,
    museumRepository: MuseumRepository = koinInject(),
) {
    val viewModel: ListViewModel = viewModel { ListViewModel(museumRepository) }
    val objects by viewModel.objects.collectAsState()

    if (objects.isNotEmpty()) {
        ObjectGrid(objects = objects, onObjectClick = { objectId ->
            navController.navigate("detail/$objectId")
        })
    } else {
        EmptyScreenContent()
    }
}


@Composable
private fun ObjectGrid(
    objects: List<MuseumObject>,
    onObjectClick: (Int) -> Unit,
) {
    Div({ classes("p-5", "grid", "grid-cols-2", "sm:grid-cols-3", "md:grid-cols-3", "lg:grid-cols-5", "gap-4") }) {
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
