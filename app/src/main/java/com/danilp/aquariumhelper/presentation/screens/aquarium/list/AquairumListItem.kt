package com.danilp.aquariumhelper.presentation.screens.aquarium.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.presentation.ui.theme.AquariumHelperTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AquariumListItem(
    aquarium: Aquarium,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.aquairum_pic),
            contentDescription = "aquarium",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(top = 6.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)
        ) {
            Text(text = aquarium.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "2 Уведомления", style = MaterialTheme.typography.labelMedium)
        }
    }

}

@Preview
@Composable
fun AquariumListItemPreview() {
    AquariumHelperTheme {
        AquariumListItem(aquarium = Aquarium.createEmpty().copy(name = "Цихлидник"))
    }
}