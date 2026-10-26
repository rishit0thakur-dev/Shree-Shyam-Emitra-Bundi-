package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

enum class AppTab(
    val titleHi: String,
    val titleEn: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    EMITRA(
        "ई-मित्र व फॉर्म",
        "E-Mitra & Forms",
        Icons.Filled.Description,
        Icons.Outlined.Description
    ),
    MOBILE_REPAIR(
        "मोबाइल रिपेयर",
        "Mobile Care",
        Icons.Filled.Build,
        Icons.Outlined.Build
    ),
    BUNDI_JOBS(
        "बूँदी प्राइवेट जॉब",
        "Bundi Jobs",
        Icons.Filled.Work,
        Icons.Outlined.WorkOutline
    ),
    SHOP_LOCATION(
        "दुकान लोकेशन",
        "Shop & Map",
        Icons.Filled.Storefront,
        Icons.Outlined.Storefront
    )
}

@Composable
fun AppBottomNav(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    isHindi: Boolean
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(16.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            AppTab.values().forEach { tab ->
                val isSelected = selectedTab == tab
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onTabSelected(tab) },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                            contentDescription = if (isHindi) tab.titleHi else tab.titleEn
                        )
                    },
                    label = {
                        Text(
                            text = if (isHindi) tab.titleHi else tab.titleEn,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            maxLines = 1
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ShyamBluePrimary,
                        selectedTextColor = ShyamBluePrimary,
                        indicatorColor = ShyamGoldLight,
                        unselectedIconColor = Color(0xFF64748B),
                        unselectedTextColor = Color(0xFF64748B)
                    )
                )
            }
        }
    }
}
