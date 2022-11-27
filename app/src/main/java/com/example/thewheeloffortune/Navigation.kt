import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thewheeloffortune.*
import com.example.thewheeloffortune.ui.theme.GameScreen


@SuppressLint("SuspiciousIndentation")
@Composable
fun Navigation(){
  val navController = rememberNavController()
    NavHost(navController = navController , startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)

        }
        composable(route = Screen.GameScreen.route){
            GameScreen(viewModel = GameScreenViewModel(), navController)

        }
        composable(route= Screen.ChooseCategoryScreen.route){
            ChooseCategoryScreen()
        }
    }
}

