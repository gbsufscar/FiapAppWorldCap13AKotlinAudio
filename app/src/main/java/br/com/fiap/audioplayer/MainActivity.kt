package br.com.fiap.audioplayer

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.audioplayer.ui.theme.AudioPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioPlayer(this) // Passa o contexto da aplicação para o componente AudioPlayer.
                }
            }
        }
    }
}

@Composable
fun AudioPlayer(context: Context) {
    /*
    Variável de estado que vai utilizar um componente chamado MediaPlayer
    capaz de reproduzir arquivos de áudio.
    O MediaPlayer precisa do contexto da aplicação e da mídia (áudio) para reproduzir.
     */
    var player = remember {
        mutableStateOf(MediaPlayer.create(context, R.raw.sem_mundial))
    }

    Box(contentAlignment = Alignment.Center) {
        Row() {

            IconButton(onClick = {
                /*
                Primeiro, se a variável player.value for nula, então instancie um objeto
                MediaPlayer com o áudio escolhido.
                Em seguida, chame o método start() para iniciar a reprodução do áudio.
                 */
                if (player.value == null){
                    player.value = MediaPlayer.create(context, R.raw.sem_mundial) // inicializa o MediaPlayer
                }
                player.value.start() // Inicia a reprodução do áudio
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = ""
                )
            }

            IconButton(onClick = {
                player.value.pause() // Pausa a reprodução do áudio
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.pause),
                    contentDescription = ""
                )
            }

            IconButton(onClick = {
                player.value.stop() // Para a reprodução do áudio
                player.value.reset() // Reseta o MediaPlayer para voltar ao estado inicial
                player.value.release() // Libera os recursos do MediaPlayer (zera a mídia)
                player.value = null // Atribui null à variável player.value
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.stop),
                    contentDescription = ""
                )
            }

        }
    }
}

