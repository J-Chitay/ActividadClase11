package com.umg.actividadclase11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.umg.actividadclase11.ui.screens.ProductsScreen
import com.umg.actividadclase11.ui.theme.Actividadclase11Theme
import com.umg.actividadclase11.ui.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Actividadclase11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductsScreen(
                        vm = productViewModel,            // ✅ Pasamos el ViewModel
                        modifier = Modifier.padding(innerPadding) // opcional si quieres padding
                    )
                }
            }
        }
    }
}

