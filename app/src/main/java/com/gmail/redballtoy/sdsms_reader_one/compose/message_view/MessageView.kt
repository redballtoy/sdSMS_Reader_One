package com.gmail.redballtoy.sdsms_reader_one.compose.message_view

import android.telephony.SmsMessage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageView(message: SmsMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center //TODO check message type
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(10.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.Blue //TODO change color by type
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "") //TODO
                Spacer(modifier = Modifier.height(16.dp))


            }


        }

    }
}