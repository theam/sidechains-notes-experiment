package com.theagilemonkeys.notes.boxes.data

import com.horizen.box.data.AbstractBoxData
import com.horizen.proposition.PublicKey25519Proposition
import com.theagilemonkeys.notes.boxes.NoteBox
import com.theagilemonkeys.notes.boxes.data.serializers.NoteBoxDataSerializer

data class NoteBoxData(val proposition: PublicKey25519Proposition, val id: String, val title: String, val content: String, val createdAt: Long):
    // Does it make sense to have to set nonce = 1 by default?
    AbstractBoxData<PublicKey25519Proposition, NoteBox, NoteBoxData>(proposition, 1) {

    override fun serializer() = NoteBoxDataSerializer()
    override fun getBox(nonce: Long): NoteBox = NoteBox(this, nonce)
}