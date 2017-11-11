package me.tatocaster.marvelapp.features.heroes.presentation.layout

import android.view.View
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Card
import com.facebook.yoga.YogaEdge
import me.tatocaster.marvelapp.data.api.response.Result

@LayoutSpec
class HeroItemSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(context: ComponentContext, @Prop hero: Result): ComponentLayout =
                Column.create(context)
                        .child(
                                Card.create(context)
                                        .content(HeroItemContent.create(context)
                                                .hero(hero)
                                        )
                                        .marginDip(YogaEdge.BOTTOM, 4f)
                                        .marginDip(YogaEdge.TOP, 4f)
                                        .clickHandler(HeroItem.onClick(context))

                        ).build()

        @OnEvent(ClickEvent::class)
        @JvmStatic
        fun onClick(c: ComponentContext, @FromEvent view: View, @Prop(optional = true) listener: View.OnClickListener?) {
            listener?.onClick(view)
        }
    }
}