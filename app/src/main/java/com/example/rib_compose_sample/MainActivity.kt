package com.example.rib_compose_sample

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext.Companion.root
import com.example.rib_compose_sample.rib.root.RootBuilder
import com.example.rib_compose_sample.ui.theme.RibcomposesampleTheme

class MainActivity : RibActivity() {

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.root)

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
    }

    override fun createRib(savedInstanceState: Bundle?): Rib {
        return RootBuilder().build(root(savedInstanceState))
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RibcomposesampleTheme {
        Greeting("Android")
    }
}