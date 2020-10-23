package buu.supakin.mathgameverdatabase


//
//fun createPlayer (player: Player): Player {
//    var  player = Player()
//    player.setPlayerId(player.playerId)
//    player.setName(player.name)
//    player.setScoreCorrect(player.scoreCorrect)
//    player.setScoreInCorrect(player.scoreInCorrect)
//    return player
//}

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