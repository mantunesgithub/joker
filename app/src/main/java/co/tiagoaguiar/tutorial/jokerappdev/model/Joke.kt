package co.tiagoaguiar.tutorial.jokerappdev.model

import com.google.gson.annotations.SerializedName

data class Joke(
    // Quando a propriedade da classe não é igual ao do Json de retorno, precisa
    // informar @SerializedName o nome que vem no Json
    @SerializedName("value") val text: String,
    @SerializedName ("icon_url") val iconUrl: String
)
