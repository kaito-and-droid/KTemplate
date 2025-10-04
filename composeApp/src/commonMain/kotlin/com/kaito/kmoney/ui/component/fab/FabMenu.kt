package com.kaito.kmoney.ui.component.fab

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class FabItem(
    val icon: @Composable () -> Unit,
    val label: String,
    val txtColor: Color = Color.Black.copy(alpha = 0.8F),
    val fabBgColor: Color = Color.Unspecified
)

@Stable
data class FabState(
    val isFabExpand: MutableState<Boolean> = mutableStateOf(false)
)

@Composable
fun rememberFabState() = remember { FabState() }

@Composable
fun FabMenu(
    icon: @Composable (State<Float>) -> Unit,
    items: SnapshotStateList<FabItem>,
    modifier: Modifier = Modifier,
    fabState: FabState = rememberFabState(),
    showLabel: Boolean = true,
    onFabClick: (FabItem) -> Unit
) {
    val transition = updateTransition(
        targetState = fabState,
        label = "fabState"
    )
    val rotateAnim = transition.animateFloat(
        transitionSpec = {
            if (fabState.isFabExpand.value) {
                spring(stiffness = Spring.StiffnessLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "rotateAnim"
    ) { state ->
        if (state.isFabExpand.value) -45F else 0F
    }
    val alphaAnim by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 200)
        }, label = "alphaAnim"
    ) { state ->
        if (state.isFabExpand.value) 1F else 0F
    }

    Box(modifier = modifier, contentAlignment = Alignment.BottomEnd) {
        items.forEachIndexed { index, item ->
            val shrinkAnim by transition.animateFloat(
                targetValueByState = { state ->
                    if (!state.isFabExpand.value) {
                        5F
                    } else {
                        (index + 1) * 60F + if (index == 0) 5F else 0F
                    }
                },
                label = "shrinkAnim",
                transitionSpec = {
                    if (targetState.isFabExpand.value) {
                        spring(stiffness = Spring.StiffnessLow, dampingRatio = 0.58F)
                    } else {
                        spring(stiffness = Spring.StiffnessMedium)
                    }
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        bottom = shrinkAnim.dp,
                        top = 5.dp,
                        end = 30.dp
                    )
                    .alpha(alphaAnim)
            ) {
                if (showLabel) {
                    Text(
                        text = item.label,
                        color = item.txtColor,
                        modifier = Modifier
                            .alpha(alphaAnim)
                            .background(color = item.fabBgColor)
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

                FloatingActionButton(
                    containerColor = if (item.fabBgColor == Color.Unspecified) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        item.fabBgColor
                    },
                    onClick = {
                        fabState.isFabExpand.value = false
                        onFabClick.invoke(item)
                    },
                    content = {
                        item.icon()
                    }
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier.padding(end = 25.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                fabState.isFabExpand.value = !fabState.isFabExpand.value
            },
            content = {
                icon(rotateAnim)
            }
        )
    }
}