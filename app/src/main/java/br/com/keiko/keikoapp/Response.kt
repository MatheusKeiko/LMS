package br.com.keiko.keikoapp

data class Response (val status: String, val msg: String){
    fun isOK()= "OK".equals(status, ignoreCase = true)
}