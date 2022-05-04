package com.theagilemonkeys.notes.core

import com.horizen.block.SidechainBlock
import com.horizen.box.Box
import com.horizen.proposition.Proposition
import com.horizen.state.ApplicationState
import com.horizen.state.SidechainStateReader
import com.horizen.transaction.BoxTransaction
import scala.util.Success
import scala.util.Try

class NotesApplicationState: ApplicationState {
    override fun validate(stateReader: SidechainStateReader?, block: SidechainBlock?) {}

    override fun validate(
        stateReader: SidechainStateReader?,
        transaction: BoxTransaction<Proposition, Box<Proposition>>?
    ) {
        TODO("Not yet implemented")
    }

    // TODO: here we expect to update notes database. The data from it will be used during validation.
    override fun onApplyChanges(
        stateReader: SidechainStateReader?,
        blockId: ByteArray?,
        newBoxes: MutableList<Box<Proposition>>?,
        boxIdsToRemove: MutableList<ByteArray>?
    ): Try<ApplicationState> = Success(this)

    // TODO: rollback notes database to certain point.
    override fun onRollback(blockId: ByteArray?): Try<ApplicationState> =
        Success(this)
}