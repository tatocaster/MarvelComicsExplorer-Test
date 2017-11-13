package me.tatocaster.marvelapp.features.comics.presentation.layout

import android.graphics.Typeface
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import com.github.pavlospt.litho.glide.GlideImage
import me.tatocaster.marvelapp.R
import me.tatocaster.marvelapp.data.api.response.Result
import java.util.*

@LayoutSpec
class ComicItemFullScreenSpec {
    companion object {

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(context: ComponentContext, @Prop comic: Result): ComponentLayout =
                Column.create(context)
                        .backgroundColor(context.resources.getColor(R.color.ghostWhite))
                        .paddingDip(YogaEdge.TOP, 8f)
                        .paddingDip(YogaEdge.BOTTOM, 8f)
                        .child(
                                Row.create(context)
                                        .child(
                                                Text.create(context)
                                                        .text(comic.title)
                                                        .textSizeSp(20f)
                                                        .textStyle(Typeface.BOLD)
                                                        .textColorRes(R.color.colorPrimary)
                                                        .build())
                                        .paddingDip(YogaEdge.LEFT, 8f)
                                        .paddingDip(YogaEdge.RIGHT, 8f)
                                        .build()
                        )
                        .child(
                                Row.create(context)
                                        .child(
                                                Text.create(context)
                                                        .text(comic.series.name)
                                                        .textSizeSp(14f)
                                                        .textColorRes(R.color.textColor)
                                                        .build())
                                        .paddingDip(YogaEdge.LEFT, 8f)
                                        .paddingDip(YogaEdge.RIGHT, 8f)
                                        .build()
                        )
                        .child(
                                GlideImage.create(context)
                                        .imageUrl(
                                                if (comic.images.isNotEmpty()) {
                                                    comic.images.get(Random().nextInt(comic.images.size)).getFullUrl()
                                                } else {
                                                    comic.thumbnail.getFullUrl()
                                                }
                                        )
                                        .placeholderImageRes(R.drawable.ic_launcher_background)
                                        .centerCrop(true)
                                        .marginDip(YogaEdge.TOP, 8f)
                                        .marginDip(YogaEdge.BOTTOM, 8f)
                                        .heightDip(150f)
                                        .build()

                        )
                        .child(
                                Row.create(context)
                                        .child(
                                                Text.create(context)
                                                        .text(comic.description)
                                                        .textSizeSp(14f)
                                                        .textColorRes(R.color.colorAccent)
                                                        .build())
                                        .paddingDip(YogaEdge.LEFT, 8f)
                                        .paddingDip(YogaEdge.RIGHT, 8f)
                                        .build()
                        )
                        .child(
                                Row.create(context)
                                        .child(
                                                Column.create(context)
                                                        .child(

                                                                Text.create(context)
                                                                        .text("Price")
                                                                        .textColorRes(R.color.grey)
                                                                        .textSizeSp(14f)
                                                                        .build()
                                                        )
                                                        .child(
                                                                Text.create(context)
                                                                        .text("$${comic.prices[0].price}")
                                                                        .textSizeSp(16f)
                                                                        .build()
                                                        )
                                                        .widthPercent(50f)
                                                        .build()
                                        )
                                        .child(
                                                Column.create(context)
                                                        .child(

                                                                Text.create(context)
                                                                        .text("Format")
                                                                        .textColorRes(R.color.grey)
                                                                        .textSizeSp(14f)
                                                                        .build()
                                                        )
                                                        .child(
                                                                Text.create(context)
                                                                        .text(comic.format)
                                                                        .textColorRes(R.color.textColor)
                                                                        .textSizeSp(16f)
                                                                        .build())
                                                        .marginDip(YogaEdge.LEFT, 8f)
                                                        .widthPercent(50f)
                                                        .build()
                                        )
                                        .paddingDip(YogaEdge.ALL, 8f)
                                        .build()
                        )
                        .build()
    }
}