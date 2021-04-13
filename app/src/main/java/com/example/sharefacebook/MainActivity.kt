package com.example.sharefacebook

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sharefacebook.databinding.ActivityMainBinding
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*
    val nomeProduto = binding.editNomeProduto.text.toString()
    val precoProduto = binding.editProductPrice.text.toString()
    val nomeMembro = binding.editMemberName.text.toString()
    val linkAppMatchfood = Uri.parse("www.minhalojaMFM.com")
    val linkSiteMatchfood = Uri.parse("https://matchfood.com/baixe_agora")


     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //shareFacebook()
        shareInstagram()
        shareWhatsapp()

    }

    fun shareFacebook(){
        binding.btnShareFacebook.setOnClickListener{

            val nomeProduto = binding.editNomeProduto.text.toString()
            val precoProduto = binding.editProductPrice.text.toString()
            val nomeMembro = binding.editMemberName.text.toString()
            val linkAppMatchfood = Uri.parse("www.minhalojaMFM.com")
            val linkSiteMatchfood = Uri.parse("https://matchfood.com/baixe_agora")

            val image = binding.imageProd.drawable
            val message = "$nomeProduto por apenas R$$precoProduto? Você só encontra" +
                    " na nossa loja virtual $nomeMembro no $linkAppMatchfood" + //@appmatchfood -> Link para o Play Store
                    "\nAceitamos várias formas de pagamento!" +
                    "\nBaixe o app Matchfood e faça seu pedido no $linkSiteMatchfood"

            val hasTag = ShareHashtag.Builder().setHashtag("#soumatchfood #incentiveocomerciolocal #matchfood").build()

            val sharecontent = ShareLinkContent.Builder()
                    .setQuote(message)
                    .setShareHashtag(hasTag) //
                    .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.matchfood.app&hl=pt_BR&gl=US")) //
                    .build()
            ShareDialog.show(this@MainActivity, sharecontent)
        }
    }

    fun shareInstagram() {

        binding.btnShareInstagram.setOnClickListener{

            val type = "image/*"
            val filename = "/image.jpg"
            val mediaPath: String = Environment.getExternalStorageDirectory().toString() + filename

            val share = Intent(Intent.ACTION_SEND)
            share.setType(type)

            val media = File(mediaPath)
            val uri = Uri.fromFile(media)

            share.putExtra(Intent.EXTRA_STREAM, uri)

            startActivity(Intent.createChooser(share, "Share to"))
        }

    }

    fun shareWhatsapp(){
        val btnShare = findViewById<Button>(R.id.btn_share_whatsapp)
        btnShare?.setOnClickListener {

            val nomeProduto = binding.editNomeProduto.text.toString()
            val precoProduto = binding.editProductPrice.text.toString()
            val nomeMembro = binding.editMemberName.text.toString()
            val linkAppMatchfood = Uri.parse("https://play.google.com/store/apps/details?id=com.matchfood.app")
            val linkSiteMatchfood = Uri.parse("https://matchfood.com/baixe_agora")

            val text: String = "*$nomeProduto* "+"por apenas"+" *R$$precoProduto?* \n\n" +
                    "Você só encontra na nossa loja *$nomeMembro* "+"no APP"+" *Matchfood* " +
                    "\uD83D\uDE03 \n"+ //emoji
                    "$linkAppMatchfood \n\n" +
                    "Aceitamos várias formas de pagamento! \uD83D\uDCB3 \n\n" + //emoji
                    "Baixe o app Matchfood e faça seu pedido: \n$linkSiteMatchfood"
            if (!text.isEmpty()) {
                startShareText(text)
            } else {
                Toast.makeText(applicationContext, "Erro", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun startShareText(text: String?) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, text)
        sendIntent.type = "text/plain"
        startActivity(sendIntent)

        // Set package only if you do not want to show all the options by which you can share the text.
        // Setting package bypass the system picker and directly share the data on WhatsApp.
        // TODO uncomment code to show whatsapp directly
        // sendIntent.setPackage("com.whatsapp");

        startActivity(sendIntent)
    }


}

