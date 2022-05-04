package com.theagilemonkeys.notes.api

import akka.http.javadsl.server.Route
import com.horizen.api.http.ApiResponse
import com.horizen.api.http.ApplicationApiGroup
import com.horizen.companion.SidechainTransactionsCompanion
import com.horizen.node.SidechainNodeView
import com.horizen.proposition.PublicKey25519PropositionSerializer
import com.horizen.utils.BytesUtils
import com.theagilemonkeys.notes.api.requests.CreateNoteRequest
import com.theagilemonkeys.notes.api.requests.DeleteNoteRequest
import com.theagilemonkeys.notes.api.requests.UpdateNoteRequest
import com.theagilemonkeys.notes.api.responses.CreateNoteResponse
import com.theagilemonkeys.notes.api.responses.ErrorResponse
import com.theagilemonkeys.notes.boxes.data.NoteBoxData
import com.theagilemonkeys.notes.transactions.NoteCreatedTransaction
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils
import java.util.*

class NotesAPI(private val sidechainTransactionsCompanion: SidechainTransactionsCompanion): ApplicationApiGroup() {
    override fun basePath(): String = "notes"

    override fun getRoutes(): MutableList<Route> = mutableListOf(
        bindPostRequest("createNote", this::createNote, CreateNoteRequest::class.java),
        bindPostRequest("updateNote", this::updateNote, UpdateNoteRequest::class.java),
        bindPostRequest("deleteNote", this::deleteNote, DeleteNoteRequest::class.java)
    )

    private fun createNote(view: SidechainNodeView, request: CreateNoteRequest): ApiResponse {
        val noteProposition = PublicKey25519PropositionSerializer.getSerializer()
            .parseBytes(BytesUtils.fromHexString(request.proposition))

        val data = NoteBoxData(noteProposition, UUID.randomUUID().toString(), request.title, request.content, view.nodeHistory.currentHeight.toLong())
        val boxes = getTransactionBoxes(view, request.fee)

        val signedTransaction = view.createSignedTransaction(boxes.inputs) { proofs ->
             NoteCreatedTransaction(
                boxes.inputs.map { it.id() }.toMutableList(),
                proofs,
                boxes.outputs,
                request.fee,
                data,
                NoteCreatedTransaction.currentVersion.toByte()
            )
        }

        return CreateNoteResponse(data.id, ByteUtils.toHexString(sidechainTransactionsCompanion.toBytes(signedTransaction)))
    }

    private fun updateNote(view: SidechainNodeView, request: UpdateNoteRequest): ApiResponse {
        /**val noteBoxOption = view.nodeState.getClosedBox(BytesUtils.fromHexString(request.id))
        if (!noteBoxOption.isPresent) {
            return ErrorResponse("Box with id ${request.id} not found")
        }

        val noteBox = noteBoxOption.get()

        val ownerSecretOption = view.nodeWallet.secretByPublicKey(noteBox.proposition())
        if (!ownerSecretOption.isPresent) {
            return ErrorResponse("Owner proposition not owned by the node")
        }

        val boxes = getTransactionBoxes(view, request.fee)*/

        TODO("Not yet implemented")
    }

    private fun deleteNote(view: SidechainNodeView, request: DeleteNoteRequest): ApiResponse {
        TODO("Not yet implemented")
    }
}