package com.theagilemonkeys.notes.api.requests

data class CreateNoteRequest(
    val title: String,
    val content: String,
    override val proposition: String,
    override val fee: Long
) : BaseRequest