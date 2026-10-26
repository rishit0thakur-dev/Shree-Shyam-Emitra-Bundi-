package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LocalJob
import com.example.model.ShopInfo
import com.example.ui.components.Card3D
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun BundiJobsScreen(
    jobs: List<LocalJob>,
    isHindi: Boolean,
    onOpenPostJob: () -> Unit,
    onOpenApplyJob: (LocalJob) -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("सभी जॉब्स") }

    val filterOptions = listOf("सभी जॉब्स", "कम्प्यूटर ऑपरेटर", "सेल्स व दुकान", "डिलीवरी", "टीचिंग", "अकाउंटेंट")

    val filteredJobs = remember(jobs, searchQuery, selectedFilter) {
        jobs.filter { j ->
            val matchesFilter = when (selectedFilter) {
                "कम्प्यूटर ऑपरेटर" -> j.titleHi.contains("कम्प्यूटर") || j.titleHi.contains("ऑपरेटर")
                "सेल्स व दुकान" -> j.titleHi.contains("सेल्स") || j.titleHi.contains("शोरूम")
                "डिलीवरी" -> j.titleHi.contains("डिलीवरी")
                "टीचिंग" -> j.titleHi.contains("शिक्षक") || j.titleHi.contains("टीचर")
                "अकाउंटेंट" -> j.titleHi.contains("अकाउंटेंट")
                else -> true
            }
            val matchesQuery = searchQuery.isBlank() ||
                    j.titleHi.contains(searchQuery, ignoreCase = true) ||
                    j.companyOrShop.contains(searchQuery, ignoreCase = true) ||
                    j.locationInBundi.contains(searchQuery, ignoreCase = true) ||
                    j.whatToDoHi.contains(searchQuery, ignoreCase = true)
            matchesFilter && matchesQuery
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Banner
        item {
            JobsHeroBanner(
                onPostJobClick = onOpenPostJob,
                onDirectCall = { IntentUtils.dialPhoneNumber(context, ShopInfo.PRIMARY_PHONE) }
            )
        }

        // Search & Filters
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("बूँदी में जॉब खोजें (उदा. ऑपरेटर, सेल्स, सदर बाजार...)", fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = ShyamBluePrimary) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(filterOptions) { opt ->
                        val isSelected = selectedFilter == opt
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedFilter = opt },
                            label = { Text(opt, fontSize = 11.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ShyamBluePrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // Section header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "बूँदी प्राइवेट जॉब रिक्तियां (${filteredJobs.size})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ShyamBlueDark
                )
                Text(
                    text = "सीधा मालिक से संपर्क",
                    fontSize = 11.sp,
                    color = EmeraldSuccessDark,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Job Cards
        items(filteredJobs, key = { it.id }) { job ->
            JobCard(
                job = job,
                onApply = { onOpenApplyJob(job) },
                onCall = {
                    val phone = if (job.contactPhone.isNotBlank()) job.contactPhone else ShopInfo.PRIMARY_PHONE
                    IntentUtils.dialPhoneNumber(context, phone)
                },
                onWhatsApp = {
                    val phone = if (job.whatsappPhone.isNotBlank()) job.whatsappPhone else ShopInfo.PRIMARY_PHONE
                    IntentUtils.openWhatsAppChat(
                        context,
                        phone,
                        "नमस्ते, मैंने श्री श्याम ई-मित्र बूँदी ऐप पर आपकी जॉब पोस्ट '${job.titleHi}' देखी है। मैं इस जॉब के लिए आवेदन करना चाहता हूँ।"
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun JobsHeroBanner(
    onPostJobClick: () -> Unit,
    onDirectCall: () -> Unit
) {
    Card3D(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(22.dp),
        elevation = 6.dp,
        borderBrush = Brush.linearGradient(listOf(ShyamGold, ShyamBlueLight))
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ShyamBlueDark,
                            ShyamBluePrimary
                        )
                    )
                )
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ShyamGold
                    ) {
                        Text(
                            text = "★ जॉब वाले भैया - बूँदी",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBlueDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Text(
                        text = "Indeed / Naukri स्टाइल",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "बूँदी में प्राइवेट जॉब पाएं\nबिना किसी एजेंट या फीस के",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "दुकान, शोरूम, ऑफिस, स्कूल व कंपनियों में सीधी भर्ती • क्या काम करना है, सैलरी व लोकेशन देखें",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onPostJobClick,
                        colors = ButtonDefaults.buttonColors(containerColor = ShyamGold),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1.2f)
                    ) {
                        Icon(Icons.Default.AddCircle, contentDescription = null, tint = ShyamBlueDark, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("नई जॉब पोस्ट करें", color = ShyamBlueDark, fontWeight = FontWeight.Black, fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = onDirectCall,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(0.8f)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("हेल्पलाइन", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun JobCard(
    job: LocalJob,
    onApply: () -> Unit,
    onCall: () -> Unit,
    onWhatsApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card3D(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = 3.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header with urgency badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = ShyamBlueContainer
                ) {
                    Text(
                        text = job.jobType,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBluePrimary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                if (job.isUrgent) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = CrimsonAlert.copy(alpha = 0.12f)
                    ) {
                        Text(
                            text = "तुरंत आवश्यकता (Urgent)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = CrimsonAlert,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Job Title
            Text(
                text = job.titleHi,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = ShyamBlueDark
            )

            // Company & Location
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Icon(Icons.Default.Storefront, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.companyOrShop,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBluePrimary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = ShyamGoldDark, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.locationInBundi,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF64748B)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Salary & Timing box
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFF8FAFC),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "वेतन (Salary):", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text(text = job.salaryText, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, color = EmeraldSuccessDark)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "समय:", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text(text = job.timings, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // What to do (काम क्या करना है)
            Text(
                text = "काम क्या करना है:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ShyamBlueDark
            )
            Text(
                text = job.whatToDoHi,
                fontSize = 12.sp,
                color = Color(0xFF334155),
                lineHeight = 16.sp,
                modifier = Modifier.padding(top = 2.dp)
            )

            // Duties bullets
            if (job.roleDuties.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                job.roleDuties.forEach { duty ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(vertical = 1.dp)
                    ) {
                        Text(text = "• ", fontSize = 11.sp, color = ShyamBluePrimary, fontWeight = FontWeight.Bold)
                        Text(text = duty, fontSize = 11.sp, color = Color(0xFF475569))
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "शैक्षणिक योग्यता: ${job.qualification}",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF64748B)
            )

            Text(
                text = "संपर्क: ${job.contactPerson} (${job.contactPhone})",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ShyamBluePrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 3 Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Call
                OutlinedButton(
                    onClick = onCall,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(0.9f)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("कॉल", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                // WhatsApp
                OutlinedButton(
                    onClick = onWhatsApp,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldSuccess),
                    modifier = Modifier.weight(1.1f)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, tint = EmeraldSuccess, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("व्हाट्सएप", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                // Apply App
                Button(
                    onClick = onApply,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                    modifier = Modifier.weight(1.1f)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("अप्लाई", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
