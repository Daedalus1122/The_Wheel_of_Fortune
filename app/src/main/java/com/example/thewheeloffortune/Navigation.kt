import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thewheeloffortune.MainActivity
import com.example.thewheeloffortune.MainScreen
import com.example.thewheeloffortune.Screen
import com.example.thewheeloffortune.ui.theme.GameScreen


@Composable
fun Navigation(){
  val navController = rememberNavController()
    NavHost(navController = navController , startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)

        }
        composable(route = Screen.GameScreen.route){
            GameScreen()

        }
    }
}

