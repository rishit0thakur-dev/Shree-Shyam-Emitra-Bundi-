package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.model.ShopInfo
import java.net.URLEncoder

object IntentUtils {

    fun dialPhoneNumber(context: Context, phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "डायल करने में असमर्थ: $phoneNumber", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsAppChat(context: Context, phoneNumber: String, message: String) {
        try {
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val cleanPhone = phoneNumber.replace("+", "").replace(" ", "").trim()
            val fullPhone = if (cleanPhone.length == 10) "91$cleanPhone" else cleanPhone
            val uri = Uri.parse("https://wa.me/$fullPhone?text=$encodedMessage")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "व्हाट्सएप खोलने में समस्या आई", Toast.LENGTH_SHORT).show()
        }
    }

    fun openShopInGoogleMaps(context: Context) {
        try {
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ShopInfo.GOOGLE_MAPS_LINK))
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            try {
                val fallbackIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode("${ShopInfo.SHOP_NAME}, ${ShopInfo.ADDRESS}")}")
                )
                context.startActivity(fallbackIntent)
            } catch (err: Exception) {
                Toast.makeText(context, "मैप्स खोलने में असमर्थ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun launchUpiPayment(context: Context, amount: Int, note: String): Boolean {
        return try {
            val uri = Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", ShopInfo.UPI_ID)
                .appendQueryParameter("pn", ShopInfo.PAYEE_NAME)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount.toString())
                .appendQueryParameter("cu", "INR")
                .build()

            val upiIntent = Intent(Intent.ACTION_VIEW, uri)
            val chooser = Intent.createChooser(upiIntent, "Pay via UPI App")
            context.startActivity(chooser)
            true
        } catch (e: Exception) {
            Toast.makeText(context, "कोई UPI ऐप (PhonePe/GPay/Paytm) नहीं मिला। QR कोड से भुगतान करें।", Toast.LENGTH_LONG).show()
            false
        }
    }

    fun shareText(context: Context, title: String, text: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, title)
                putExtra(Intent.EXTRA_TEXT, text)
            }
            context.startActivity(Intent.createChooser(shareIntent, title))
        } catch (e: Exception) {
            Toast.makeText(context, "साझा करने में असमर्थ", Toast.LENGTH_SHORT).show()
        }
    }
}
