package com.theagilemonkeys.notes.api.responses

data class CreateNoteResponse(val id: String, override val transactionBytes: String): NoteAPIResponse