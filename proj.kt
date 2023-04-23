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
    fun remakeHeder(id:Int,newHeder:String,date:String){
        notes[id].remakeHeader(newHeder,date)
    }
    fun remakeText(id:Int,newText:String,date:String){
        notes[id].remakeText(newText,date)
    }
}

fun main(){
    var noteBase=NoteBase()
    var i:Int
    var com:String
    while (true){
        i=0
        for (note in noteBase.getAll()){
            println("${i}.${note}")
            i++
        }
        println("Выерете что вы хотите сделать")
        println("add-создать заметку, read-прочитать заметку, red-изменить заметку, что бы выйти введите exit")
        com=readLine().toString().lowercase()
        when(com){
            "add" -> {
                println("Введите название заметки, а затем в следующей строке текст")
                noteBase.add(readLine().toString(), readLine().toString(), LocalDate.now().toString())
            }
            "read" -> {
                println("Введите номер заметки")
                for (text in noteBase.get(readLine()?.toInt() ?:0)){
                    println(text)
                }
            }
            "red" -> {
                println("Что вы хотите изменить? Введите heder если заголовок, и text если текст заметки")
                com=readLine().toString().lowercase()
                when(com){
                    "heder" -> {
                        println("Введите номер заметки, а затем новый заголовок")
                        noteBase.remakeHeder(readLine()?.toIntOrNull() ?:0, readLine().toString(), LocalDate.now().toString())
                    }
                    "text" -> {
                        println("Введите номер заметки, а затем новый текст")
                        noteBase.remakeText(readLine()?.toIntOrNull() ?:0, readLine().toString(), LocalDate.now().toString())
                    }
                }
            }
            "exit" -> {
                break
            }
        }
    }
}
