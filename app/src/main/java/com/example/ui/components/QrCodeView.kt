package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ShopInfo
import kotlin.math.abs

@Composable
fun QrCodeView(
    amount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(14.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(
                modifier = Modifier
                    .size(190.dp)
            ) {
                val cellSize = size.width / 25f
                val darkColor = Color(0xFF0F172A)
                val accentColor = Color(0xFF1E3A8A)

                // Draw Position Finder Patterns (Top-Left, Top-Right, Bottom-Left)
                fun drawFinderPattern(xCell: Float, yCell: Float) {
                    val x = xCell * cellSize
                    val y = yCell * cellSize
                    val finderSize = 7 * cellSize

                    // Outer box
                    drawRoundRect(
                        color = darkColor,
                        topLeft = Offset(x, y),
                        size = Size(finderSize, finderSize),
                        cornerRadius = CornerRadius(8f, 8f)
                    )
                    // Inner white
                    drawRoundRect(
                        color = Color.White,
                        topLeft = Offset(x + cellSize, y + cellSize),
                        size = Size(finderSize - 2 * cellSize, finderSize - 2 * cellSize),
                        cornerRadius = CornerRadius(4f, 4f)
                    )
                    // Center solid block
                    drawRoundRect(
                        color = accentColor,
                        topLeft = Offset(x + 2 * cellSize, y + 2 * cellSize),
                        size = Size(finderSize - 4 * cellSize, finderSize - 4 * cellSize),
                        cornerRadius = CornerRadius(3f, 3f)
                    )
                }

                drawFinderPattern(1f, 1f)
                drawFinderPattern(17f, 1f)
                drawFinderPattern(1f, 17f)

                // Deterministic QR data matrix pattern based on ShopInfo & amount
                val seed = (ShopInfo.UPI_ID.hashCode() xor amount).toLong()
                for (row in 0 until 25) {
                    for (col in 0 until 25) {
                        // Skip finder patterns
                        val inFinderTL = row in 1..7 && col in 1..7
                        val inFinderTR = row in 1..7 && col in 17..23
                        val inFinderBL = row in 17..23 && col in 1..7
                        val inCenter = row in 11..13 && col in 11..13

                        if (!inFinderTL && !inFinderTR && !inFinderBL && !inCenter) {
                            val pseudoRandom = abs((row * 31 + col * 17 + seed * 13).toInt() % 7)
                            if (pseudoRandom == 0 || pseudoRandom == 2 || pseudoRandom == 5) {
                                drawRect(
                                    color = if ((row + col) % 3 == 0) accentColor else darkColor,
                                    topLeft = Offset(col * cellSize, row * cellSize),
                                    size = Size(cellSize * 0.92f, cellSize * 0.92f)
                                )
                            }
                        }
                    }
                }

                // Center UPI logo emblem
                val centerSize = 3.5f * cellSize
                val centerX = 12.5f * cellSize - centerSize / 2f
                val centerY = 12.5f * cellSize - centerSize / 2f
                drawRoundRect(
                    color = Color(0xFFF59E0B),
                    topLeft = Offset(centerX, centerY),
                    size = Size(centerSize, centerSize),
                    cornerRadius = CornerRadius(6f, 6f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "UPI: ${ShopInfo.UPI_ID}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A)
            )
            Text(
                text = "राशि: ₹$amount (Shree Shyam E-mitra)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF475569)
            )
        }
    }
}
