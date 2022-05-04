package com.theagilemonkeys.notes.api.requests

data class UpdateNoteRequest(
    val id: String,
    val content: String,
    override val proposition: String,
    override val fee: Long
) : BaseRequest