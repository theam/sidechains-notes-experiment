package com.theagilemonkeys.notes.api.responses

data class UpdateNoteResponse(val id: String, override val transactionBytes: String): NoteAPIResponse