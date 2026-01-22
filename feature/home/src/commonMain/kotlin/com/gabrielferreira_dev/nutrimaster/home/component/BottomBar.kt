package com.gabrielferreira_dev.nutrimaster.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gabrielferreira_dev.nutrimaster.home.domain.BottomBarDestination
import com.nutrimaster.shared.IconPrimary
import com.nutrimaster.shared.IconSecondary
import com.nutrimaster.shared.SurfaceLighter
import org.jetbrains.compose.resources.painterResource

//esse modifier é apenas para tirar ao clicar no icone bordas grandes
fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )
}
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selected: BottomBarDestination,
    onSelect: (BottomBarDestination) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 12.dp))
            .background(SurfaceLighter)
            .padding(
                vertical = 24.dp,
                horizontal = 36.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BottomBarDestination.entries.forEach { destination ->
            val animatedTint by animateColorAsState(
                targetValue = if (selected == destination) IconSecondary else IconPrimary,
                label = "icone de anicacao Tint"
            )
            
            /*box pra nao clicar direto no icone, mais clicar no box que aumenta a zona de click*/

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickableWithoutRipple { onSelect(destination) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .clickableWithoutRipple { (onSelect(destination)) },
                    painter = painterResource(destination.icon),
                    contentDescription = destination.name,
                    tint = animatedTint
                )
            }
        }
    }
}