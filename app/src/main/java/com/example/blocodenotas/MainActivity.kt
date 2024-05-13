package com.example.blocodenotas

import BLACK
import BlocoDeNotasTheme
import GOLD
import WHITE
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blocodenotas.datastore.StoreAnotacao
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlocoDeNotasTheme {
                BlocoDeNotas()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BlocoDeNotas() {

    val context = LocalContext.current
    
    val scope = rememberCoroutineScope()
    
    val storeAnotacao = StoreAnotacao(context)

    val anotacaoSalva = storeAnotacao.getAnotacao().collectAsState(initial = "")
    
    var anotacao by remember { mutableStateOf("") }

    anotacao = anotacaoSalva.value

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = GOLD
            ) {
                Text(
                    text = "Bloco de Notas",
                    color = BLACK,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch { 
                        storeAnotacao.setAnotacao(anotacao)
                        Toast.makeText(context, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                },
                backgroundColor = GOLD,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 8.dp
                )
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_salvar),
                    contentDescription = "Ícone de salvar anotação"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)
        ) {
            androidx.compose.material.TextField(
                value = anotacao,
                onValueChange = {
                    anotacao = it
                },
                label = {
                    Text(
                        text = "Digite sua anotação..."
                    )
                },
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = WHITE,
                    cursorColor = GOLD,
                    focusedLabelColor = WHITE
                )
            )
        }
    }

}


@Preview
@Composable
fun BlocoDeNotasPreview() {
    BlocoDeNotas()
}
