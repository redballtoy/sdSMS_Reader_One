package com.gmail.redballtoy.sdsms_reader_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.gmail.redballtoy.sdsms_reader_one.navigation.SetupNavGraph
import com.gmail.redballtoy.sdsms_reader_one.ui.theme.SdSMS_Reader_OneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SdSMS_Reader_OneTheme {
                val navController= rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}