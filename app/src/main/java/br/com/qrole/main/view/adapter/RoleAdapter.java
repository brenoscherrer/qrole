package br.com.qrole.main.view.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.qrole.main.R;
import br.com.qrole.main.entities.Role;
import br.com.qrole.main.utilities.BitmapUtilities;
import br.com.qrole.main.utilities.StringUtilities;
import br.com.qrole.main.view.activities.LoginActivity;

/**
 * Role Adapter to fill the content of the List Item.
 */
public class RoleAdapter extends ArrayAdapter<Role> {
    private Context context;
    private List<Role> roles = null;

    public RoleAdapter(Context context, List<Role> roles) {
        super(context, 0, roles);
        this.roles = roles;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Role role = roles.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_role, null);
        }

        ImageView imageViewImage = (ImageView) view.findViewById(R.id.image_view_role);
        if (!StringUtilities.isBlank(role.getImage())) {
            imageViewImage.setImageBitmap(BitmapUtilities.getBitmapFromBase64String(role.getImage()));
        } else {
            // Exibo a imagem de "sem imagem"
            imageViewImage.setImageDrawable(context.getResources().getDrawable(R.drawable.noimage,
                    null));
        }

        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_role_title);
        textViewTitle.setText(role.getTitle());

        TextView textViewDescription = (TextView) view.findViewById(R.id.text_view_role_description);
        textViewDescription.setText(role.getDescription());

        return view;
    }

    public void doFilter(String query) {
        if (!StringUtilities.isBlank(query)) {
            if (roles != null && !roles.isEmpty()) {
                List<Role> newRoles = new ArrayList<>();

                for (Role role : roles) {
                    if (role.getDescription().toUpperCase().contains(query.toUpperCase())) {
                        newRoles.add(role);
                    }
                }

                clear();
                addAll(newRoles);

                notifyDataSetChanged();
            }
        }
    }
}
