
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kmtestdiza.SecondScreenActivity
import com.example.kmtestdiza.User
import com.example.kmtestdiza.databinding.ItemLayoutBinding

class UserAdapter(private val context: Context, private val userList: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            // Menggunakan Glide untuk memuat gambar dari URL
            Glide.with(context)
                .load(user.avatar)
                .apply(RequestOptions.circleCropTransform()) // Membuat gambar menjadi bulat
                .into(binding.profilePicture)


            binding.firstName.text = user.first_name
            binding.lastName.text = user.last_name
            binding.email.text = user.email
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedUser = userList[position]

                    // Mendapatkan referensi ke SharedPreferences menggunakan konteks dari adapter
                    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    // Mengatur nilai ke SharedPreferences
                    editor.putString("USERNAME_API_KEY", clickedUser.first_name)
                    editor.apply()


                    // Menjalankan intent dan menutup aktivitas
                    val intent = Intent(context, SecondScreenActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }
        }
    }
    fun updateList(newList: List<User>) {
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }
}
