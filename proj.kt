import java.time.LocalDate

class Note(var id: Int, var header: String, var text: String, var date: String) {
    fun give(): MutableList<String> {
        val formattedText = mutableListOf<String>()
        formattedText.add("\n ~-~->  $header  <-~-~")
        formattedText.add("\n $text")
        formattedText.add("\n date $date")
        return formattedText
    }

    fun giveHeader(): String {
        return header
    }

    fun remakeHeader(newHeader: String, date: String) {
        header = newHeader
    }

    fun remakeText(newText: String, date: String) {
        text = newText
    }
}

class NoteBase() {
    private val notes = mutableListOf<Note>()

    fun getAll(): List<String> {
        var headers = mutableListOf<String>()
        for (note in notes) {
            headers.add(note.giveHeader())
        }
        return headers
    }
    fun get(id:Int): List<String>{
        return notes[id].give()
    }
    fun add(header:String,text:String,date:String){
        notes.add(Note(notes.size+1,header,text,date))
    }
}

fun main(){
    val noteBase = NoteBase()
    var chto:String
    var i:Int
    println("add-добавить заметку, red-список всех заметок, rec-прочитать конкретную заметку")
    while (true){
        chto=readLine().toString().lowercase()
        when(chto){
            "add" -> noteBase.add(readLine().toString(), readLine().toString(), LocalDate.now().toString())

            "red" -> {
                i=0
                for (note in noteBase.getAll()){
                    println(note)
                    println(i)
                    println()
                    i++
                }
            }

            "rec" -> {
                for (note in noteBase.get(readLine()?.toInt() ?:0)){
                    println(note)
                }
            }

        }
        println("Ok")
    }
}
