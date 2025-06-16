package com.gayuh.xox.ui.component

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayuh.xox.utils.updateLocale
import java.util.Locale

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun LanguageToggle() {
    val context = LocalContext.current
    val activity = context as? Activity
    val currentLang = Locale.getDefault().language
    val isEnglish = currentLang == "en"
    var langState by rememberSaveable { mutableStateOf(currentLang) }

    val dotOffset by animateDpAsState(
        targetValue = if (isEnglish) 24.dp else 6.dp,
        label = "DotOffset"
    )

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text("🇮🇩", fontSize = 20.sp)

            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.LightGray)
                    .clickable {
                        langState = if (!isEnglish) "en" else "id"
                        activity?.updateLocale(langState)
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .offset(x = dotOffset)
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color.DarkGray)
                )
            }

            Text("🇬🇧", fontSize = 20.sp)
        }
    }

}
