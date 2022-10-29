package isla.del.lago.shenglong.extensions

import com.fasterxml.jackson.databind.ObjectMapper

fun Any.objectToJson(): String {
    val objectWriter = ObjectMapper().writer()

    return objectWriter.writeValueAsString(this)
}