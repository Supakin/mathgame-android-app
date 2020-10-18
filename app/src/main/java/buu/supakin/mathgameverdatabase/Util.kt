package buu.supakin.mathgameverdatabase

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.core.text.HtmlCompat
import buu.supakin.mathgameverdatabase.database.PlayerTable
import buu.supakin.mathgameverdatabase.models.Player
import java.lang.StringBuilder

fun createPlayer (playerTable: PlayerTable): Player {
    var  player = Player()
    player.setPlayerId(playerTable.playerId)
    player.setName(playerTable.name)
    player.setScoreCorrect(playerTable.scoreCorrect)
    player.setScoreInCorrect(playerTable.scoreInCorrect)
    return player
}

fun validatePlayerNameIsEmpty (playerName: String?): Boolean {
    return playerName == null
}

fun validatePlayerNameIsLong (playerName: String): Boolean {
    return playerName.length > 8
}

//fun formatRanking(playerList: List<PlayerTable>, resources: Resources): Spanned {
//    val stringBuilder = StringBuilder()
//    stringBuilder.apply {
//        playerList.forEach {
//            append("<b>${it.name}</b> \t\t ${it.scoreCorrect - it.scoreInCorrect}")
//        }
//    }
//
//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY)
//    } else {
//        HtmlCompat.fromHtml(stringBuilder.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
//    }
//}