package com.jetbrains.kmpapp.screens.list


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import dev.kilua.core.IComponent
import dev.kilua.html.div
import dev.kilua.html.h3
import dev.kilua.html.img
import dev.kilua.html.p
import org.koin.compose.koinInject

@Composable
fun IComponent.ListScreen(
    museumRepository: MuseumRepository = koinInject(),
) {
    val viewModel: ListViewModel = viewModel { ListViewModel(museumRepository) }
    val objects by viewModel.objects.collectAsState()

    if (objects.isNotEmpty()) {
        ObjectGrid(objects = objects, onObjectClick = { objectId ->
//            navController.navigate("detail/$objectId")
        })
    } else {
        EmptyScreenContent()
    }
}


@Composable
private fun IComponent.ObjectGrid(
    objects: List<MuseumObject>,
    onObjectClick: (Int) -> Unit,
) {
    div {
        className(
            listOf(
                "p-5",
                "grid",
                "grid-cols-2",
                "sm:grid-cols-3",
                "md:grid-cols-3",
                "lg:grid-cols-5",
                "gap-4"
            ).joinToString(" ")
        )
        objects.forEach { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.objectID) },
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
        p {
            className("text-slate-500 dark:text-slate-400 mt-2 text-sm")
            +obj.artistDisplayName
        }
        p {
            className("text-slate-500 dark:text-slate-400 mt-2 text-sm")
            +obj.objectDate
        }
    }
}

