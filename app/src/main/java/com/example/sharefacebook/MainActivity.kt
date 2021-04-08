package com.example.sharefacebook

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sharefacebook.databinding.ActivityMainBinding
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shareFacebook()

    }

    fun shareFacebook(){
        binding.btnShare.setOnClickListener{

            val nomeProduto = binding.editNomeProduto.text.toString()
            val precoProduto = binding.editProductPrice.text.toString()
            val nomeMembro = binding.editMemberName.text.toString()

            val image = binding.imageProd.drawable
            val message = "${(nomeProduto)} por apenas R$${(precoProduto)}? Você só encontra" +
                    " na nossa loja virtual ${(nomeMembro)} no @appmatchfood" + //@appmatchfood -> Link para o Play Store
                    "\nAceitamos várias formas de pagamento!" +
                    "\nRealize seu pedido no @appmatchfood"

            var hasTag = ShareHashtag.Builder().setHashtag("#Matchfood").build()

            var sharecontent = ShareLinkContent.Builder()
                    .setQuote(message)
                    .setShareHashtag(hasTag) //
                    .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.matchfood.app&hl=pt_BR&gl=US")) //
                    .build()
            ShareDialog.show(this@MainActivity, sharecontent)
        }
    }



}

