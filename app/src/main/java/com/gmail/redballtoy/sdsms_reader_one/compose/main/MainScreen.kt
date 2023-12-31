package com.gmail.redballtoy.sdsms_reader_one.compose.main

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.gmail.redballtoy.sdsms_reader_one.compose.message_view.MessageView
import com.gmail.redballtoy.sdsms_reader_one.compose.message_view.SenderView
import com.gmail.redballtoy.sdsms_reader_one.model.SMSMessage
import com.gmail.redballtoy.sdsms_reader_one.model.parseDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val allMessages = remember { mutableStateMapOf<String, List<SMSMessage>>() }

    LaunchedEffect(key1 = Unit) {
        //all messages that were received and sent for union as thread
        val messages = readMassages(context = context, "inbox") + readMassages(
            context = context, type = "sent"
        )
        //sender will be the map key for messages and the actual value will be a list of messages
        allMessages += messages.sortedBy { it.date }.groupBy { it.sender }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        allMessages.forEach { (sender, messages) ->
            stickyHeader(key = sender) {
                SenderView(sender = sender)
            }

            messages.groupBy { it.date.parseDate().split(" ").first() }
                .forEach { (date, smsMessages) ->
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(0.38f),
                            text = date,
                            textAlign = TextAlign.Start,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    items(
                        items = smsMessages,
                        key = { it.date }
                    ) {
                        MessageView(message = it)
                    }
                }
        }
    }
}

private fun readMassages(context: Context, type: String): List<SMSMessage> {
    val messages = mutableListOf<SMSMessage>()
    //content resolver as a bridge between the application and the android database
    val cursor = context.contentResolver.query(
        Uri.parse("content://sms/$type"),
        null,
        null,
        null,
        null
    )
    cursor?.use {
        val indexMessage = it.getColumnIndex("body")
        val indexSender = it.getColumnIndex("address")
        val indexDate = it.getColumnIndex("date")
        val indexRead = it.getColumnIndex("read")
        val indexType = it.getColumnIndex("type")
        val indexThread = it.getColumnIndex("thread_id")
        val indexService = it.getColumnIndex("service_center")

        while (it.moveToNext()) {
            messages.add(
                SMSMessage(
                    message = it.getString(indexMessage),
                    sender = it.getString(indexSender),
                    date = it.getLong(indexDate),
                    read = it.getString(indexRead).toBoolean(),
                    type = it.getInt(indexType),
                    thread = it.getInt(indexThread),
                    service = it.getString(indexService)?: ""
                )
            )
        }
    }
    return messages
}