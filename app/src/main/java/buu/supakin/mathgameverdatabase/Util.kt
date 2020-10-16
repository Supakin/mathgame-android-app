package buu.supakin.mathgameverdatabase

import buu.supakin.mathgameverdatabase.database.PlayerTable
import buu.supakin.mathgameverdatabase.models.Player

fun createPlayer (playerTable: PlayerTable): Player {
    var  player = Player()
    player.setPlayerId(playerTable.playerId)
    player.setName(playerTable.name)
    player.setScoreCorrect(playerTable.scoreCorrect)
    player.setScoreInCorrect(playerTable.scoreInCorrect)
    return player
}

fun validatePlayerNameIsEmpty (playerName: String): Boolean {
    if (playerName == null) return false
    return true
}

fun validatePlayerNameIsLong (playerName: String): Boolean {
    if (playerName.length > 8) return false
    return true
}