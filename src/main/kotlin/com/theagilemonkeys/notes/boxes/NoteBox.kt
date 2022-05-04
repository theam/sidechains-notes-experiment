package com.theagilemonkeys.notes.boxes

import com.horizen.box.AbstractBox
import com.horizen.proposition.PublicKey25519Proposition
import com.theagilemonkeys.notes.boxes.data.NoteBoxData
import com.theagilemonkeys.notes.boxes.serializers.NoteBoxSerializer

data class NoteBox(val data: NoteBoxData, val nonce: Long): AbstractBox<PublicKey25519Proposition, NoteBoxData, NoteBox>(data, nonce) {
    override fun serializer() = NoteBoxSerializer()
    override fun boxTypeId(): Byte = NotesAppBoxes.NoteBox.id
}