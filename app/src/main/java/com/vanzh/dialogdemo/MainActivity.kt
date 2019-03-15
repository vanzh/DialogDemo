package com.vanzh.dialogdemo

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import io.reactivex.Observable
import okhttp3.*
import java.io.IOException
import java.io.InputStream
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.*
import javax.net.ssl.*

class MainActivity : AppCompatActivity() {
    private lateinit var mOkHttpClient: OkHttpClient;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(v: View) {
        val tips = "你已在一个圈子中，加入新的圈子后，你将从当前圈子退出，并清零当前圈子所有贡献。圈子1天内只能更换1次哟。"
        CirclePublicChoiceDialog.builder(supportFragmentManager)
            .makeTopTitleDesc("")
            .makeTitleDesc(tips)
            .makeEscDesc("取消")
            .makeOkDesc("确定加入")
            .show(object : OnDialogFragmentClickListener {
                override fun onCancelClick() {

                }

                override fun onOkClick(mDialog: DialogFragment?, extra: Any?) {

                }
            })
        testRxKotlin()
        initOkhttp()
    }

    private fun testRxKotlin() {
        val a = intArrayOf(1, 2, 3)
        var subscribe = Observable.fromArray(a)
            .doOnNext {
                //  throw IllegalArgumentException("error")
            }
            .subscribe({
                println("Yes")
            }, {
                println(it.message)
            })
    }

    private fun initOkhttp() {

        mOkHttpClient = OkHttpClient.Builder().sslSocketFactory(getFactory())
            .build()
        val str = "{\"username\":\"18523048537\",\"password\",\"111111\"}"
        val body = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), str)
        val req: Request = Request.Builder().post(body).url("https://api.j1home.com/api/Accounts/login").build()

        mOkHttpClient.newCall(req).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                Log.e("Okhttp onResponse", response?.body()?.source().toString())
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Okhttp onFailure", e?.message)
            }

        });

    }

    private fun getFactory(): SSLSocketFactory? {

        var sslSocketFactory: SSLSocketFactory? = null
        try {
            val inputStream: InputStream = assets.open("fcb619bd18c877b2.crt") // 得到证书的输入流


            var trustManager: TrustManager? = trustManagerForCertificates(inputStream)//以流的方式读入证书
            val sslContext = SSLContext.getInstance("TLS")
            if (trustManager != null) {
                sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
            }
            sslSocketFactory = sslContext.socketFactory

        } catch (e: GeneralSecurityException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return sslSocketFactory;
    }

    @Throws(GeneralSecurityException::class)
    private fun trustManagerForCertificates(`in`: InputStream): TrustManager {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificates = certificateFactory.generateCertificates(`in`)
        if (certificates.isEmpty()) {
            throw IllegalArgumentException("expected non-empty set of trusted certificates")
        }

        // Put the certificates a key store.
        val password = "password".toCharArray() // Any password will work.
        val keyStore = newEmptyKeyStore(password)
        var index = 0
        for (certificate in certificates) {
            val certificateAlias = Integer.toString(index++)
            keyStore.setCertificateEntry(certificateAlias, certificate)
        }

        // Use it to build an X509 trust manager.
        val keyManagerFactory = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm()
        )
        keyManagerFactory.init(keyStore, password)
        val trustManagerFactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm()
        )
        trustManagerFactory.init(keyStore)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }

    /**
     * 添加password
     *
     * @param password
     * @return
     * @throws GeneralSecurityException
     */
    @Throws(GeneralSecurityException::class)
    private fun newEmptyKeyStore(password: CharArray): KeyStore {
        try {
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType()) // 这里添加自定义的密码，默认
            val `in`: InputStream? = null // By convention, 'null' creates an empty key store.
            keyStore.load(`in`, password)
            return keyStore
        } catch (e: IOException) {
            throw AssertionError(e)
        }

    }
}
