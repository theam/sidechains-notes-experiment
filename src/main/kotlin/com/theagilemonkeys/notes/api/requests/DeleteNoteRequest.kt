package com.theagilemonkeys.notes.api.requests

data class DeleteNoteRequest(val id: String, override val proposition: String, override val fee: Long) : BaseRequest