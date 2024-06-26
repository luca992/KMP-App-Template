package com.jetbrains.kmpapp.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.MR
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.jetbrains.compose.web.svg.fill
import org.jetbrains.compose.web.svg.xmlns
import org.w3c.dom.svg.SVGElement
import stringResource

data class DetailScreen(val objectId: Int) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: DetailScreenModel = getScreenModel()

        val obj by screenModel.getObject(objectId).collectAsState(initial = null)
        obj?.let {
            ObjectDetails(obj = it, onBackClick = { navigator.pop() })
        } ?: EmptyScreenContent()
    }
}

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
private fun ChevronLeft(attrs: AttrBuilderContext<SVGElement>? = null) {
    Svg(viewBox = "0 0 24 24", attrs = {
        classes("w-6", "h-6")
        xmlns("http://www.w3.org/2000/svg")
        fill("none")
        attr("stroke-width", "1.5")
        attr("stroke", "currentColor")
        attrs?.invoke(this)
    }) {
        Path(d = "M15.75 19.5 8.25 12l7.5-7.5", attrs = {
            attr("stroke-linecap", "round")
            attr("stroke-linejoin", "round")
        })
    }
}

@Composable
private fun ObjectDetails(
    obj: MuseumObject,
    onBackClick: () -> Unit,
) {
    Nav {
        Div({
            classes("p-3", "bg-white", "border-gray-200", "dark:bg-gray-900", "dark:border-gray-700", "shadow-md")
        }) {
            Div({ classes("inline-flex", "p-2", "hover:bg-blue-200", "rounded-lg") }) {
                ChevronLeft {
                    onClick { onBackClick() }
                }
            }
        }
    }
    Div({ classes("p-5") }) {
        Img(src = obj.primaryImageSmall, alt = obj.title, attrs = {
            classes("h-36", "mb-3")
        })

        H3({
            classes(
                "text-slate-900", "dark:text-white", "my-5", "text-base", "font-bold", "tracking-tight",
            )
        }) {
            Text(obj.title)
        }

        LabeledInfo(stringResource(MR.strings.label_title), obj.title)
        LabeledInfo(stringResource(MR.strings.label_artist), obj.artistDisplayName)
        LabeledInfo(stringResource(MR.strings.label_date), obj.objectDate)
        LabeledInfo(stringResource(MR.strings.label_dimensions), obj.dimensions)
        LabeledInfo(stringResource(MR.strings.label_medium), obj.medium)
        LabeledInfo(stringResource(MR.strings.label_department), obj.department)
        LabeledInfo(stringResource(MR.strings.label_repository), obj.repository)
        LabeledInfo(stringResource(MR.strings.label_credits), obj.creditLine)
    }
}


@Composable
private fun LabeledInfo(
    label: String,
    data: String,
) {
    Div({ classes("flex") }) {
        Div({ classes("font-bold", "mr-1") }) { Text("$label:") }
        Div { Text(data) }
    }
}
