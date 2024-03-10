//package com.jetbrains.kmpapp.screens.detail
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.koin.getScreenModel
//import cafe.adriel.voyager.navigator.LocalNavigator
//import cafe.adriel.voyager.navigator.currentOrThrow
//import com.jetbrains.kmpapp.data.MuseumObject
//import com.jetbrains.kmpapp.screens.EmptyScreenContent
//
//data class DetailScreen(val objectId: Int) : Screen {
//    @Composable
//    override fun Content() {
//        val navigator = LocalNavigator.currentOrThrow
//        val screenModel: DetailScreenModel = getScreenModel()
//
//        val obj by screenModel.getObject(objectId).collectAsState(initial = null)
//        obj?.let {
//            ObjectDetails(obj = it, onBackClick = { navigator.pop() })
//        } ?: EmptyScreenContent()
//    }
//}
//
//@Composable
//private fun ObjectDetails(
//    obj: MuseumObject,
//    onBackClick: () -> Unit,
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {},
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(Icons.Default.ArrowBack, stringResource(Res.string.back))
//                    }
//                })
//        },
//        modifier = modifier,
//    ) { paddingValues ->
//        Column(
//            Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(paddingValues)
//        ) {
//            KamelImage(
//                resource = asyncPainterResource(data = obj.primaryImageSmall),
//                contentDescription = obj.title,
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.LightGray)
//            )
//
//            SelectionContainer {
//                Column(Modifier.padding(12.dp)) {
//                    Text(obj.title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
//                    Spacer(Modifier.height(6.dp))
//                    LabeledInfo(stringResource(Res.string.label_title), obj.title)
//                    LabeledInfo(stringResource(Res.string.label_artist), obj.artistDisplayName)
//                    LabeledInfo(stringResource(Res.string.label_date), obj.objectDate)
//                    LabeledInfo(stringResource(Res.string.label_dimensions), obj.dimensions)
//                    LabeledInfo(stringResource(Res.string.label_medium), obj.medium)
//                    LabeledInfo(stringResource(Res.string.label_department), obj.department)
//                    LabeledInfo(stringResource(Res.string.label_repository), obj.repository)
//                    LabeledInfo(stringResource(Res.string.label_credits), obj.creditLine)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun LabeledInfo(
//    label: String,
//    data: String,
//    modifier: Modifier = Modifier,
//) {
//    Column(modifier.padding(vertical = 4.dp)) {
//        Spacer(Modifier.height(6.dp))
//        Text(
//            buildAnnotatedString {
//                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
//                    append("$label: ")
//                }
//                append(data)
//            }
//        )
//    }
//}
